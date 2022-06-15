class Sentence {

    constructor(id, sentenceText, answers) {
        this.id = id;
        this.sentenceText = sentenceText;
        this.answers = answers;
    }

    shuffleAnswers() {
        for (var i = 0; i < this.answers.length; i++) {
            var randomIndex = Math.floor(Math.random() * this.answers.length);
            var temp = this.answers[i];
            this.answers[i] = this.answers[randomIndex];
            this.answers[randomIndex] = temp;
        }
    }

}