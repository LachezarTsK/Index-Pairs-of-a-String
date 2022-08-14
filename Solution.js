
/**
 * @param {string} text
 * @param {string[]} words
 * @return {number[][]}
 */
var indexPairs = function (text, words) {
    this.ALPHABET_SIZE = 26;
    this.ASCII_SMALL_CASE_A = 97;
    this.charToStartIndexes = new Array(this.ALPHABET_SIZE).fill([]);
    for (let i = 0; i < text.length; ++i) {
        charToStartIndexes[text.codePointAt(0) - this.ASCII_SMALL_CASE_A].push(i);
    }

    const wordMatches = [];
    findWordMatches(text, words, wordMatches);
    wordMatches.sort((x, y) => (x[0] === y[0]) ? (x[1] - y[1]) : (x[0] - y[0]));

    return wordMatches;
};

/**
 * @param {string} text
 * @param {string[]} words
 * @param {number[][]} wordMatches
 */
function findWordMatches(text, words, wordMatches) {
    for (let word of words) {
        const indexes = this.charToStartIndexes[word.codePointAt(0) - this.ASCII_SMALL_CASE_A];
        for (let index of indexes) {
            if (text.startsWith(word, index)) {
                wordMatches.push([index, index + word.length - 1]);
            }
        }
    }
}
