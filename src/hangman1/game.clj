(ns  hangman1.game
  (:require [clojure.string :refer [includes?]]))

(defn word->letters
  "Converts a string to an array of chars."
  [word]
  (mapv str word))

(defn game-won?
  "Liefert true, wenn der Spieler das Spiel `game` gewonnen hat,
  andernfalls false.
  See also [[game-over?]]"
  [{:keys [word-to-guess correct-guesses]}]
  (= (->>
      (word->letters word-to-guess)
      (set))
     correct-guesses))

(defn game-lost?
  "Returns true if player lost, otherwise false.
  See also [[game-over?]]"
  [{:keys [tries-left]}]
  (zero? tries-left))

(defn game-over?
  "Returns true if user guessed the word or has no tries-left,
  otherwise false. See also [[game-won?]] and [[game-lost?]]."
  [game]
  (or (game-won? game) (game-lost? game)))

(defn new-game
  "Creates a new `game`, parameter is a string."
  [word-to-guess]
  {:word-to-guess  word-to-guess
   :correct-guesses #{}
   :tries-left 5})

(defn guess-letter
  "Returns an updated `game` reflecting whether the `letter`
  the user guessed was contained in the `word-to-guess`;
  is the `game` over, the function returns the `game`
  without change."
  [{:keys [word-to-guess] :as game} letter]
  (cond
    (game-over? game) game
    (includes? word-to-guess letter)
    (update game :correct-guesses conj letter)
    :else (update game :tries-left dec)))

(defn score
  "Returns the current `score`."
  [{:keys [correct-guesses]}]
  (-> correct-guesses
      (count)
      (* 8)))

(defn hint
  "Returns a vector of `correct-guesses` in correct position;
  incorrectly guessed letters are indicated by nil."
  [{:keys [word-to-guess correct-guesses]}]
  (->>
   word-to-guess
   (word->letters)
   (mapv correct-guesses)))

(defn myguess
  "version 2"
  [letterv game]
  (if
   (= letterv [])
    game
    (do (myguess
         (rest letterv)
         (guess-letter game (first letterv))) (println game))))

;; Zu ratendes Wort: "Doctronic". Geratene Buchstaben: e n s t r o i l k
;; Das Ergebnis ist der daraus resultierende Spielzustsand.
(comment
  (reduce
   guess-letter
   (new-game "doctronic")
   ["e" "n" "s" "t" "r" "o" "i" "l" "k"]))

;; Zu ratendes Wort: "Doctronic". Geratene Buchstaben: e n s t r o i l k
;; Das Ergebnis sind  alle  Spielzustände, die sich aus den geratenen
;; Buchstaben ergeben
(comment
  (reductions
   guess-letter
   (new-game "doctronic")
   ["e" "n" "s" "t" "r" "o" "i" "l" "k"]))

;; Zu ratendes Wort: "Doctronic". Geratene Buchstaben: e n s t r o i l k
;; Ergebnis von hint auf die Spielzustände
(comment
  (map #(hint %)
       (reductions
        guess-letter
        (new-game "doctronic")
        ["e" "n" "s" "t" "r" "o" "i" "l" "k"])))

;; Zu ratendes Wort: "Doctronic". Geratene Buchstaben: e n s t r o i l k
;; Ergebnis von score auf die Spielzustände
(comment
  (map #(score %)
       (reductions
        guess-letter
        (new-game "doctronic")
        ["e" "n" "s" "t" "r" "o" "i" "l" "k"])))
