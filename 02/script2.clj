(require '[clojure.string :as str])
(defn lazyLineSeq [] (line-seq (clojure.java.io/reader "/Users/arturskowronski/Priv/AdventOfCode/2018/02/input")))
(defn letterSeq [lineSeq] (map #(str/split % #"") (lineSeq)))

(defn split [seq number] (map vector 
    (map #(clojure.string/join "" %) (map #(take number %) (seq)))
    (map #(clojure.string/join "" %) (map #(drop number %) (seq)))
))

(defn allowedStrings [seq]
    (set (map first (filter #(< 1 (last %)) (frequencies seq))))
)

(defn stringsAndDups [seq] (map vector 
    (map #(contains? (allowedStrings (map first seq)) %) (map first seq))
    (map #(contains? (allowedStrings (map last seq)) %) (map last seq))
    seq))

(defn newSeq [seq] 
    (map #(clojure.string/join "" %) (map last (filter #(some true? %) (stringsAndDups seq))))
)

(count (newSeq (split lazyLineSeq 14)))

(defn split14 [] (map vector 
    (map #(clojure.string/join "" %) (map #(take 15 %) (newSeq (split lazyLineSeq 14))))
    (map #(clojure.string/join "" %) (map #(drop 15 %) (newSeq (split lazyLineSeq 14))))
))

(defn split15 [] (map vector 
    (map #(clojure.string/join "" %) (map #(take 16 %) (newSeq (split14))))
    (map #(clojure.string/join "" %) (map #(drop 16 %) (newSeq (split14))))
))

(defn split16 [] (map vector 
    (map #(clojure.string/join "" %) (map #(take 17 %) (newSeq (split15))))
    (map #(clojure.string/join "" %) (map #(drop 17 %) (newSeq (split15))))
))

(defn split17 [] (map vector 
    (map #(clojure.string/join "" %) (map #(take 18 %) (newSeq (split16))))
    (map #(clojure.string/join "" %) (map #(drop 18 %) (newSeq (split16))))
))

(defn split18 [] (map vector 
    (map #(clojure.string/join "" %) (map #(take 19 %) (newSeq (split17))))
    (map #(clojure.string/join "" %) (map #(drop 19 %) (newSeq (split17))))
))

(defn split19 [] (map vector 
    (map #(clojure.string/join "" %) (map #(take 20 %) (newSeq (split18))))
    (map #(clojure.string/join "" %) (map #(drop 20 %) (newSeq (split18))))
))

(defn split20 [] (map vector 
    (map #(clojure.string/join "" %) (map #(take 1 %) (newSeq (split19))))
    (map #(clojure.string/join "" %) (map #(drop 1 %) (newSeq (split19))))
))

(defn split21 [] (map vector 
    (map #(clojure.string/join "" %) (map #(take 5 %) (newSeq (split20))))
    (map #(clojure.string/join "" %) (map #(drop 5 %) (newSeq (split20))))
))

(defn split22 [] (map vector 
    (map #(clojure.string/join "" %) (map #(take 10 %) (newSeq (split21))))
    (map #(clojure.string/join "" %) (map #(drop 10 %) (newSeq (split21))))
))


(defn split23 [] (map vector 
    (map #(clojure.string/join "" %) (map #(take 8 %) (newSeq (split22))))
    (map #(clojure.string/join "" %) (map #(drop 8 %) (newSeq (split22))))
))


(newSeq (split23))
