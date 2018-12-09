(require '[clj-time.format :as f])
(require '[clj-time.core :as t])

(def input-line-seq (line-seq (clojure.java.io/reader "/Users/arturskowronski/Priv/AdventOfCode/2018/04/input")))
(defn as-integer [el] (Integer/parseInt el))

(defn format-date [date-str] (f/parse (f/formatter "yyyyMMddHHmm") date-str))
    
(defn event [line] (let [[_ year month day hour minute command](re-matches #"\[(.*)\-(.*)\-(.*) (.*):(.*)\] (.*)" line)]
    {:date (format-date (str year month day hour minute)) :command command}
))

(defn events [seq] (map event seq))
(defn sort-events-by-date [events] (sort-by :date events))

(def sorted-events (sort-events-by-date (events input-line-seq)))

(defn is-shift-beginning [el] (re-matches #"Guard #(.*) begins shift" (first (vals (select-keys el [:command])))))

(defn extract-guard-id [line] (let [[_ guard](re-matches #"Guard #(.*) begins shift" (nth (first line) 3))] guard))
(defn extract-guard-id_ [line] (let [[_ guard](is-shift-beginning line)] guard))

(defn partition-shifts [seq] (take-while #(nil? (is-shift-beginning %)) seq))

(def by-id (loop [seq sorted-events, accumulator []]
    (if (empty? seq)
      accumulator
      (recur (drop (+ (count (partition-shifts (rest seq))) 1) seq) (concat accumulator [{:id (extract-guard-id_ (first seq)) :opions (partition-shifts (rest seq))}]))))
)

(def group-by-id (group-by :id by-id))
(defn group-entry [el] [(first el) (partition 2 (flatten (map second (map second (second el)))))] )

(defn sleep-periods [el] [(first el) (map #(t/in-minutes (t/interval (val (first (first %))) (val (first (second %))))) (second el))] )
(defn sleep-minutes [el] [(first el) (sort-by val (frequencies (flatten (map #(range (t/minute (val (first (first %)))) (t/minute (val (first (second %))))) (second el)))))] )

(def grouped-entry (map group-entry group-by-id))
(def sleepovers (map #(reduce + (second %)) (map sleep-periods grouped-entry)))
(def max-sleepover (.indexOf sleepovers (apply max sleepovers)))
(defn most-sleep [el] [(first el) (last (last (second el))) (first (last (second el)))])

(println "TASK 1")
(println (* (as-integer (first (nth (map sleep-minutes grouped-entry) max-sleepover))) (first (last (last (nth (map sleep-minutes grouped-entry) max-sleepover))))))
(println "TASK 2")
(println (let [
    most-minutes-asleep (last (sort-by second (map most-sleep (map sleep-minutes grouped-entry))))
](reduce * [(as-integer (first most-minutes-asleep)) (last most-minutes-asleep)])))

