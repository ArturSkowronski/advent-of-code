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
