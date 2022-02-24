(ns game
  (:require [clojure.string :refer [includes?]]))

(defn word->letters
  "Converts a string to an array of chars"
  [word]
  (mapv str word))

(defn game-won?
  "Liefert true, wenn der Spieler das Spiel `game `gewonnen hat,
  andernfalls false. "
  [{:keys [word-to-guess correct-guesses]}]
  (= (into (set "") (word->letters word-to-guess)) correct-guesses))

(defn game-lost?
  "Returns true if player lost, otherwise false."
  [{:keys [tries-left]}]
  (zero? tries-left))

(defn game-over?
  "Returns true if user guessed the word or has no tries left,
  otherwise false."
  [game]
  (or (game-won? game) (game-lost? game)))

(defn new-game
  "creates a new game, parameter is a string"
  [word-to-guess]
  {:word-to-guess  word-to-guess
   :correct-guesses '(set "")
   :tries-left 5})

(defn guess-letter
  "Returns an updated game reflecting whether the letter the user
  guessed was contained in the word-to-guess;
  is the game over, the function returns the game without change."
  [{:keys [word-to-guess] :as game} letter]
  (cond
    (game-over? game) game
    (includes? word-to-guess letter)
    (update-in game [:correct-guesses] conj letter)
    :else (update-in game [:tries-left] dec)))

(defn score
  "Returns the current score."
  [{:keys [correct-guesses]}]
  (-> correct-guesses
      (count)
      (* 8)))

(defn hint
  "Returns a vector of correctly guessed letters in correct position;
  not yet correctly guessed letters are indicated by nil."
  [{:keys [word-to-guess correct-guesses]}]
  (->>
      word-to-guess
      (word->letters)
      (mapv correct-guesses)
   ))




