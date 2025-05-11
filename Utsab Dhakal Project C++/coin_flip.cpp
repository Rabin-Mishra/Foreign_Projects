// Coin Flip Game
// I have taken the ASCII Art from: https://www.tricksters.org/ph14/asciiart/ & https://en.rakko.tools/tools/68

// Including the necessary C++ standard libraries
#include <iostream>
#include <string>
#include <vector>
#include <ctime>
#include <cstdlib>
#include <algorithm> // Added for std::remove_if

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

    // Now, printing the ASCII art corresponding to the badGuesses value
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

    // Introduction screen with ASCII art taken from tricksters and rakko tools
    cout << " .----------------.  .----------------.  .----------------.  .----------------.  .----------------.\n"
            "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n"
            "| | _____  _____ | || |  _________   | || |    _______   | || |      __      | || | ____   ____  | |\n"
            "| ||_   _||_   _|| || | |  _   _  |  | || |   /  ___  |  | || |     /  \\     | || ||_  _| |_  _| | |\n"
            "| |  | |    | |  | || | |_/ | | \\_|  | || |  |  (__ \\_|  | || |    / /\\ \\    | || |  \\ \\   / /   | |\n"
            "| |  | '    ' |  | || |     | |      | || |   '.___`-.   | || |   / ____ \\   | || |   \\ \\ / /    | |\n"
            "| |   \\ `--' /   | || |    _| |_     | || |  |`\\____) |  | || | _/ /    \\ \\_ | || |    \\ ' /     | |\n"
            "| |    `.__.'    | || |   |_____|    | || |  |_______.'  | || ||____|  |____|| || |     \\_/      | |\n"
            "| |              | || |              | || |              | || |              | || |              | |\n"
            "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n"
            " '----------------'  '----------------'  '----------------'  '----------------'  '----------------' \n"
            "Welcome to the Coin Flip Game!\n"
            "Guess the crypto currency to win the jackpot.\n";

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

        // Find the position of the guessed letter in lettersRemaining
        size_t position = lettersRemaining.find(letter);

        // If the letter is found
        if (position != string::npos)
        {
            // Remove the letter from lettersRemaining
            lettersRemaining.erase(position, 1);
        }

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

        // Checking if the player has won (guessPhrase matches secretPhrase)
        if (guessPhrase == secretPhrase)
        {
            // Win
            system("cls");
            cout << " .----------------.  .----------------.  .-----------------.\n"
                    "| .--------------. || .--------------. || .--------------. |\n"
                    "| | _____  _____ | || |     ____     | || | ____  _____  | |\n"
                    "| ||_   _||_   _|| || |   .'    `.   | || ||_   \\|_   _| | |\n"
                    "| |  | | /\\ | |  | || |  /  .--.  \\  | || |  |   \\ | |   | |\n"
                    "| |  | |/  \\| |  | || |  | |    | |  | || |  | |\\ \\| |   | |\n"
                    "| |  |   /\\   |  | || |  \\  `--'  /  | || | _| |_\\   |_  | |\n"
                    "| |  |__/  \\__|  | || |   `.____.'   | || ||_____|\____| | |\n"
                    "| |              | || |              | || |              | |\n"
                    "| '--------------' || '--------------' || '--------------' |\n"
                    " '----------------'  '----------------'  '----------------' \n"
                 << " Congratulations !You guessed the phrase : " << secretPhrase << endl;
            break;
        }
        else if (badGuesses == 6)
        {
            // Lose
            system("cls");
            cout << " .----------------.  .----------------.  .----------------.  .----------------.\n"
                    "| .--------------. || .--------------. || .--------------. || .--------------. |\n"
                    "| |   _____      | || |     ____     | || |    _______   | || |  _________   | |\n"
                    "| |  |_   _|     | || |   .'    `.   | || |   /  ___  |  | || | |  _   _  |  | |\n"
                    "| |    | |       | || |  /  .--.  \\  | || |  |  (__ \\_|  | || | |_/ | | \\_|  | |\n"
                    "| |    | |   _   | || |  | |    | |  | || |   '.___`-.   | || |     | |      | |\n"
                    "| |   _| |__/ |  | || |  \\  `--'  /  | || |  |`\\____) |  | || |    _| |_     | |\n"
                    "| |  |________|  | || |   `.____.'   | || |  |_______.'  | || |   |_____|    | |\n"
                    "| |              | || |              | || |              | || |              | |\n"
                    "| '--------------' || '--------------' || '--------------' || '--------------' |\n"
                    " '----------------'  '----------------'  '----------------'  '----------------' \n"
                 << "Sorry, you lost. The phrase was: " << secretPhrase << endl;
            break;
        }
    }

    return 0;
}