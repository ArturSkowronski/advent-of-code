(require '[clojure.string :as str])

(def input-line-seq (seq (char-array (first (line-seq (clojure.java.io/reader "/Users/arturskowronski/Priv/AdventOfCode/2018/05/input"))))))

(defn partitioned-data [seq] (partition-all 2 seq))
(defn map-pair-cal [pair] [(int (first pair)) (int (second pair)) pair])
(defn map-pair [pair] (if (= (count pair) 1)
    pair
    (map-pair-cal pair)))

(defn shorten-cal [pair] [(Math/abs (- (first pair) (second pair))) (nth pair 2)])

(defn shorten [pair] (if (= (count pair) 1)
     '(0 0 pair)
     (shorten-cal pair)))

(defn filter-protons [value] (not= 32 (first value)))
(defn filtered-pairs [seq] (flatten (map second (filter filter-protons (map shorten (map map-pair (partitioned-data seq)))))))
(defn filtered-pairs2 [seq] (concat [(first (filtered-pairs seq))] (filtered-pairs (rest (filtered-pairs seq)))))

(def letters (seq (char-array "abcdefghijklmnopqrstuvwxyz")))
(defn filter-letter [seq letter] (filter #(not= (char (- (int letter) 32)) %) (filter #(not= letter %) seq)))

(defn with-removed-letter [letter] (loop [seq (filter-letter input-line-seq letter)]
    (println (count seq))
    (if (= (count seq) (count (filtered-pairs2 seq)))
      (println letter (count seq))
      (recur (filtered-pairs2 seq))
)))

(run! println (map with-removed-letter letters))