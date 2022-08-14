
#include <vector>
using namespace std;

class Solution {
    
    inline static const int ALPHABET_SIZE = 26;
    array<vector<int>, ALPHABET_SIZE> charToStartIndexes{};

public:
    vector<vector<int>> indexPairs(string text, vector<string>& words) {
        for (int i = 0; i < text.length(); ++i) {
            charToStartIndexes[text[i] - 'a'].push_back(i);
        }

        vector<vector<int>> wordMatches;
        findWordMatches(text, words, wordMatches);
        
        const auto smallerFirst = [](const vector<int>& x, const vector<int>& y) {return (x[0] == y[0]) ? (x[1] < y[1]) : (x[0] < y[0]);};
        sort(wordMatches.begin(), wordMatches.end(), smallerFirst);

        return wordMatches;
    }

private:
    void findWordMatches(const string& text, const vector<string>& words, vector<vector<int>>& wordMatches) {
        for (const auto& word : words) {
            vector<int>& indexes = charToStartIndexes[word[0] - 'a'];
            for (int index : indexes) {
                if (text.substr(index, word.length()).find(word) != text.npos) {
                    wordMatches.push_back(vector<int>{index, index + static_cast<int> (word.length()) - 1});
                }
            }
        }
    }
};
