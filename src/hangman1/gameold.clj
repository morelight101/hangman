(ns game
  (:gen-class))
(refer 'clojure.string)


(def data
  {:global
    {:word-population ["Palm" "Tree" "guide"]
    :allowed-letters ["a" "b" "c" ]
    :points-value 8
    :max-guesses 5
     }

   :specific-game 
    {:word-to-be-guessed  ""      ; Eine Textzeichenkette
     :tries-left          5       ; Eine Ganzzahl
     :correct-guesses     ""      ; Eine Menge von Buchstaben (jeder Buchstabe als String, wenn das einfacher ist)
     }
   }
  )


(defn game-won?
  "compares whether :correct-guesses = word-to-be-guessed"
  []
  (let [{{:keys [word-to-be-guessed correct-guesses]} :specific-game} data
        wtbg 
        (into #{} (split @word-to-be-guessed #"\B"))
        ;;converts from string -> vector of strings -> set
        ]
    (= wtbg @correct-guesses)))



(defn game-lost?  
  "checks whether any tries are left"
  []
  (let [{{:keys [tries-left]} :specific-game} data]
    (zero? @tries-left)))


(defn game-over?
  []
  (or (game-won?)(game-lost?)))


(defn new-game
  "resets :specific-game"
  []
  (let [{{:keys [word-to-be-guessed tries-left correct-guesses]} :specific-game}
        data
        {{:keys [word-population]} :global}
        data
        new-word (nth word-population (rand-int (count word-population)))
        ]

    (reset! word-to-be-guessed new-word)
    (reset! tries-left 5)
    (reset! correct-guesses #{})))






