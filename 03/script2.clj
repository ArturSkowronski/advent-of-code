(defn lineSeq [] (line-seq (clojure.java.io/reader "/Users/arturskowronski/Priv/AdventOfCode/2018/03/input")))

(defn asInteger [el] (Integer/parseInt el))
(defn linePoints [line] (let [[_ id left top wide tall](re-matches #"(.*) @ (.*),(.*): (.*)x(.*)" line)]
    (for [x (range (asInteger left) (+ (asInteger left) (asInteger wide)))
          y (range (asInteger top) (+ (asInteger top) (asInteger tall)))]
    [x y]
)))

(defn points [seq] (map #(linePoints %) seq))

(defn lineStruct [line] (let [[_ id](re-matches #"(.*) @ (.*),(.*): (.*)x(.*)" line)]
    [id (linePoints line)]  
))
(defn lines [seq] (map #(lineStruct %) seq))

(defn singularPoints [seq] (map #(first %) (filter (comp #{1} last) (frequencies (apply concat (points seq))))))
(defn vectorizedSingularPoints [seq] (vec (singularPoints seq)))
; (println (singularPoints (lineSeq)))
; (println (every? (singularPoints (lineSeq)) ))
; (println (every? (singularPoints (lineSeq)) (map #(last %) (take 1 (lines (lineSeq))))))
; (println (every? (vec (singularPoints (lineSeq))) ))
; (println (vec '([704 926] [704 927] [704 928] [704 929] [705 926] [705 927] [705 928] [705 929] [706 926] [706 927] [706 928] [706 929] [707 926] [707 927] [707 928] [707 929] [708 926] [708 927] [708 928] [708 929])))

(def singularPoints (vec (singularPoints (lineSeq))))

(defn singularPointFound [point] (some #(= point %) singularPoints))

(defn singularLineFound [line] (every? true? (map singularPointFound line)))
; (println (singularLineFound [[17 730] [537 6222222]]))
; (println (map #(singularLineFound %) (first (map #(last %) (take 1 (lines (lineSeq)))))))
(run! println (map #(str (clojure.string/join ", " (take 1 %)) (singularLineFound %)) (map #(last %) (lines (lineSeq)))))