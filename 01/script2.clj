(defn lazyLineSeq [] (line-seq (clojure.java.io/reader "/Users/arturskowronski/Priv/AdventOfCode/01/input")))
(defn lazyCycle [i] (frequencies (take i (reductions + (map  #(Integer/parseInt %) (cycle (lazyLineSeq)))))))

(loop [i 0]  
    (println i)
    (if (= 0 (count (filter (comp #{2} last) (lazyCycle i)))) 
        (recur (inc i))
        (println (last (vals (lazyCycle i))))  
    )
)