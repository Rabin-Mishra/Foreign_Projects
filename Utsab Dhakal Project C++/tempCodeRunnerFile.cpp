// Coin Flip Game
// I have taken the ASCII Art from: https://www.tricksters.org/ph14/asciiart/

// Including the necessary C++ standard libraries
#include <iostream>
#include <string>
#include <vector>
#include <ctime>
#include <cstdlib>

using namespace std;

string getRandomWord(const vector<string> &wordList)
{
    // seeding the random number generator with current time
    srand(time(0));
    int randomIndex = rand() % wordList.size();
    return wordList[randomIndex];
}
// Function to print the ASCII art based on number of bad guesses
void printArt(int badGuesses)
{
    // Defining an array of ASCII art strings for different bad guess counts
    string art[] = {
        "   _____ \n"
        "  /     \\ \n"
        " | O   O |\n"
        " |   -   |\n"
        " |   -   |\n"
        " |_______|\n",

        "   _____ \n"
        "  /     \\ \n"
        " | O   O |--\n"
        " |   -   |\n"
        " |   -   |\n"
        " |_______|\n",

        "   _____ \n"
        "  /     \\ \n"
        " | O   O |--\n"
        " |   -   |--\n"
        " |   -   |\n"
        " |_______|\n",

        "   _____ \n"
        "  /     \\ \n"
        " | O   O |--\n"
        " |   -   |--\n"
        " |   -   |--\n"
        " |_______|\n",

        "   _____ \n"
        "  /     \\ \n"
        " | O   O |--\n"
        " |   X   |--\n"
        " |   -   |--\n"
        " |_______|\n",

        "   _____ \n"
        "  /     \\ \n"
        " | X   X |--\n"
        " |   X   |--\n"
        " |   -   |--\n"
        " |_______|\n",

        "   _____ \n"
        "  /     \\ \n"
        " | X   X |--\n"
        " |   X   |--\n"
        " |   X   |--\n"
        " |_______|\n"};
    // Now ,printing the ASCII art corresponding to the badGuessses value
    cout << art[badGuesses] << endl;
}

int main()
{
    // Defining a vector of cryptocurrency names to choose from
    vector<string> wordList = {"bitcoin", "ethereum", "litecoin", "ripple", "dogecoin"};
    string secretPhrase = getRandomWord(wordList);
    string guessPhrase(secretPhrase.length(), '.');
    int badGuesses = 0;
    string letters;
    string lettersRemaining = "abcdefghijklmnopqrstuvwxyz";

    // Introduction screen with ASCII art taken from tricksters
    cout << "   __________       .__                 __   \n"
         << "  /  _____/  |    __| _/   _______    |__| __\n"
         << " /   \\  ___  |   / __ |   /  ___/\\    |  |/ /\n"
         << " \\    \\_\\  \\ |   / /_/ |   \\___ \\ \\   |    < \n"
         << "  \\______  / |__\\____ |  /____  > \\  |__|_ \\\n"
         << "         \\/         \\/        \\/   \\/     \\/\n"
         << "Welcome to the Coin Flip Game!\n"
         << "Guess the crypto currency to win the jackpot.\n";

    while (true)
    {
        system("clear");
        printArt(badGuesses);

        // Displaying letters remaining
        cout << "Letters remaining: " << lettersRemaining << endl;

        // Output guess phrase
        cout << "Guess: " << guessPhrase << endl;

        // Prompting for letter
        char letter;
        cout << "> ";
        cin >> letter;
        letter = tolower(letter);
        letters += letter;

        // Removing letter from remaining letters
        lettersRemaining.erase(remove(lettersRemaining.begin(), lettersRemaining.end(), letter), lettersRemaining.end());

        // Checking if letter is in secret phrase
        bool letterFound = false;
        string::size_type pos = 0;
        while ((pos = secretPhrase.find(letter, pos)) != string::npos)
        {
            guessPhrase[pos] = letter;
            letterFound = true;
            pos++;
        }

        if (!letterFound)
        {
            badGuesses++;
        }
        // checking if the player has won(guessPhrase matches secretPhrase)
        if (guessPhrase == secretPhrase)
        {
            // Win
            system("clear");
            cout << "   _____ \n"
                 << "  /     \\ \n"
                 << " | O   O |--\n"
                 << " |   -   |--\n"
                 << " |   -   |--\n"
                 << " |_______|\n"
                 << "Congratulations! You guessed the phrase: " << secretPhrase << endl;
            break;
        }
        else if (badGuesses == 6)
        {
            // Lose
            system("clear");
            cout << "   _____ \n"
                 << "  /     \\ \n"
                 << " | X   X |--\n"
                 << " |   X   |--\n"
                 << " |   X   |--\n"
                 << " |_______|\n"
                 << "Sorry, you lost. The phrase was: " << secretPhrase << endl;
            break;
        }
    }

    return 0;
}