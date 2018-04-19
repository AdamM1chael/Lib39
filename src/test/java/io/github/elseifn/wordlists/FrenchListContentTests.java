/*
 *  BIP39 library, a Java implementation of BIP39
 *  Copyright (C) 2017-2019 Tongjian Cui, elseifn
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *  Original source: https://github.com/elseifn/Lib39
 *  You can contact the authors via github issues.
 */

package io.github.elseifn.wordlists;

import io.github.elseifn.lib39.WordList;
import io.github.elseifn.lib39.wordlists.French;
import org.junit.Test;

import static io.github.elseifn.wordlists.WordListHashing.WORD_COUNT;
import static org.junit.Assert.assertEquals;

public final class FrenchListContentTests {
    private final WordList wordList = French.INSTANCE;

    @Test
    public void hashCheck() {
        assertEquals("9e515b24c9bb0119eaf18acf85a8303c4b8fec82dac53ad688e20f379de1286c",
                WordListHashing.hashWordList(wordList));
    }

    @Test
    public void normalizedHashCheck() {
        assertEquals("922939bd934c6128a897ad299de471bd7aafe578d28a37370e881dc998903d51",
                WordListHashing.hashWordListNormalized(wordList));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void correctNumberOfWords() {
        wordList.getWord(WORD_COUNT + 1);
    }
}
