package catdany.proj.rhyme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Rhyme
{
	/**
	 * List of all vowels
	 * @see Rhyme#isVowel(char)
	 */
	public static final char[] vowels =
		{
			'�', '�', '�', '�', '�', '�', '�', '�', '�', '�'
		};
	/**
	 * List of all consonants
	 * @see Rhyme#isConsonant(char)
	 */
	public static final char[] consonants =
		{
			'�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�'
		};
	
	/**
	 * RNG instance.
	 */
	private static final Random random = new Random();
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(Local.WELCOME);
		while (true)
		{
			System.out.print("> ");
			String word = r.readLine();
			String ending = getEnding(word);
			if (ending == null)
			{
				continue;
			}
			for (int i = 0; i < 5; i++)
			{
				System.out.println(generateWord(ending));
			}
		}
	}
	
	/**
	 * Get the ending of the given word (everything after the emphasis)<br>
	 * Example: ba`nana => nana
	 * @param word
	 * @return
	 */
	public static String getEnding(String word)
	{
		if (word.split("`").length > 2)
		{
			System.out.println(Local.TOO_MANY_EMPHASES);
			return null;
		}
		else if (!word.contains("`"))
		{
			System.out.println(Local.EMPHASIS_NOT_SPECIFIED);
			return null;
		}
		return word.split("`", 2)[1];
	}
	
	/**
	 * Generate a word with a given ending (supposed to rhyme with other words with this ending)
	 * @param ending
	 * @return
	 */
	public static String generateWord(String ending)
	{
		String word = "";
		int syllables = random.nextInt(2) + 1;
		for (int i = 0; i < syllables; i++)
		{
			word += pullConsonant();
			word += pullVowel();
		}
		if (random.nextBoolean())
		{
			word += pullConsonant();
		}
		return word + ending;
	}
	
	/**
	 * Get a random vowel from {@link Rhyme#vowels}
	 * @return
	 */
	public static char pullVowel()
	{
		return vowels[random.nextInt(vowels.length)];
	}
	
	/**
	 * Get a random consonant from {@link Rhyme#consonants}
	 * @return
	 */
	public static char pullConsonant()
	{
		return consonants[random.nextInt(consonants.length)];
	}
	
	/**
	 * Check if a given character is a vowel
	 * @see Rhyme#vowels
	 * @see Rhyme#isConsonant(char)
	 * @param c
	 * @return true/false
	 */
	public static boolean isVowel(char c)
	{
		for (char i : vowels)
		{
			if (i == c)
			{
				return true;
			}
		}
		return false;
	}
}
