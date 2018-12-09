(require '[clojure.string :as str])
(defn lazyLineSeq [] (line-seq (clojure.java.io/reader "/Users/arturskowronski/Priv/AdventOfCode/2018/02/input")))
(defn letterSeq [lineSeq] (map #(str/split % #"") (lineSeq)))
(defn eq [num seq] (some #(= num %) seq))
(defn eq2 [seq] (eq 2 seq))
(defn eq3 [seq] (eq 3 seq))
(*
    (count (filter true? (map eq2 (map vals (map frequencies (letterSeq lazyLineSeq))))))
    (count (filter true? (map eq3 (map vals (map frequencies (letterSeq lazyLineSeq))))))
)
