(ns hangman1.core
  (:gen-class))

(require '[clojure.string :as str])


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn select-word []
  (let [words (get data :word-population)]
  (nth  words (rand-int (count words)))))
 
;; below the prototype of the datastructure
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; (def data
;;   {:global
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; choice of datastructure
According to 
(see https://purelyfunctional.tv/guide/clojure-collections/)
Clojure offers the following datastructures:
    Vector
    HashMap
    Sorted Map
    Set
    Sorted Set
    List
Queue



;; global values are truly immutable, they are to be initialized according to game rules
;; i.e. points-value 8, etc. 
;; {
;;  :word-population ;; vector: 
;;  :allowed-letters ;;  
;;  :points-value    ;; 8 how many points your receive for a valid guesses 
;;  :max-guesses     ;; 5 how many times are you allowed to guess before you lose
;;  :reset-key       ;; which key does a user need to hit to start a new game
;; } 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;   :specific-game
;;   {
;;    :word-to-be-guessed  
;;    :user-input []
;; valid guesses is the core of the application
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; all other variables derive from this vector, in particular the functions being tested as eg
;; valid-word?
;; guessed-letters
;; correct-guesses
;; total-points
;; remaining-letters
;; win?
;; lose?
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; When the game starts the :word-to-be-guessed is initialized
;; With each valid guess the variable: user-input accumulates another value
;; The game state can be reconstructed from these two variables.
;; lose? or win? and all functions in between are the result of these two values
;;
;;   }
;; }
;;)
;;)

;;ein frisch gestartetes Spiel

(def data
  {:global
   {:word-population ["Palm" "Tree" "guide"]
    :allowed-letters ["a" "b" ...]
    :points-value 8
    :max-guesses 5
    }

   :specific-game
   {:word-to-be-guessed "Palm"
    :user-input []
    }
   }
)
;;ein verlorenes Spiel (der Spieler konnte das Wort nicht erraten)
(def data
  {:global
   {:word-population ["Palm" "Tree" "guide"]
    :allowed-letters ["a" "b" ...]
    :points-value 8
    :max-guesses 5}

   :specific-game
   {:word-to-be-guessed "Tree"
    :user-input ["1" "a" "b" "c" "d" "e"]}})

;;ein gewonnenes Spiel (der Spieler konnte das Wort erraten)
(def data
  {:global
   {:word-population ["Palm" "Tree" "guide"]
    :allowed-letters ["a" "b" ...]
    :points-value 8
    :max-guesses 5}

   :specific-game
   {:word-to-be-guessed "Palm"
    :user-input ["2" "P" "a" "l" "m"]}})

;;ein laufendes Spiel
(def data
  {:global
   {:word-population ["Palm" "Tree" "guide"]
    :allowed-letters ["a" "b" ...]
    :points-value 8
    :max-guesses 5}

   :specific-game
   {:word-to-be-guessed "Palm"
    :user-input ["P" "a"]}})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

