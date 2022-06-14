class SentencesContainer {

    constructor(sentences) {
        this.sentences = sentences;
        this.currentSentence = 0;
    }

    shuffleSentences() {
        for (var i = 0; i < this.sentences.length; i++) {
            var randomIndex = Math.floor(Math.random() * this.sentences.length);
            var temp = this.sentences[i];
            this.sentences[i] = this.sentences[randomIndex];
            this.sentences[randomIndex] = temp;
        }
    }

    shuffleAnswers() {
        for (var i = 0; i < this.sentences.length; i++) {
            this.sentences[i].shuffleAnswers();
        }
    }

    reachedEnd() {
        return this.currentSentence >= this.sentences.length;
    }

}