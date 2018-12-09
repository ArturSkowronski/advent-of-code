(defn lineSeq [] (line-seq (clojure.java.io/reader "/Users/arturskowronski/Priv/AdventOfCode/2018/03/input")))

(defn asInteger [el] (Integer/parseInt el))
(defn linePoints [line] (let [[_ _ left top wide tall](re-matches #"(.*) @ (.*),(.*): (.*)x(.*)" line)]
    (for [x (range (asInteger left) (+ (asInteger left) (asInteger wide)))
          y (range (asInteger top) (+ (asInteger top) (asInteger tall)))]
    [x y]
)))

(defn points [seq] (map #(linePoints %) (seq)))
(println (count (filter #(< 1 (last %)) (frequencies (apply concat (points lineSeq)))))