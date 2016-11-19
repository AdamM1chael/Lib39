# Lib39

Apart from generating a seed, only English, French, Spanish and Japanese [currently packaged](https://github.com/elseifn/Lib39), but as `WordList` is an interface and you can provide your own.

# Install

Use either of these repositories:

```
repositories {
    jcenter()
}
```

Add dependency:

```
dependencies {
    compile 'io.github.elseifn:Lib39:2018.02.14'
}

```

# Usage

## Generate a mnemonic

Using a `StringBuilder`:

```java
StringBuilder sb = new StringBuilder();
byte[] entropy = new byte[Words.TWELVE.byteLength()];
new SecureRandom().nextBytes(entropy);
new MnemonicGenerator(English.INSTANCE)
    .createMnemonic(entropy, sb::append);
System.out.println(sb.toString());
```

## Validate a mnemonic

```java
try {
    MnemonicValidator
        .ofWordList(English.INSTANCE)
        .validate(mnemonic);
} catch (UnexpectedWhiteSpaceException e) {
   ...
} catch (InvalidWordCountException e) {
    ...
} catch (InvalidChecksumException e) {
     ...
} catch (WordNotFoundException e) {
    ...
    //e.getSuggestion1()
    //e.getSuggestion2()
}
```

Or if you have a list of words from a word list:

```java
MnemonicValidator
        .ofWordList(English.INSTANCE)
        .validate(mnemonicWordsInAList);
```

## Generate a seed

As does not use a word list, can be used now for any language.

```java
byte[] seed = new SeedCalculator().calculateSeed(mnemonic, passphrase);
```

Or if you have a list of words from a word list:

```java
byte[] seed = new SeedCalculator()
                     .withWordsFromWordList(English.INSTANCE)
                     .calculateSeed(mnemonicWordsInAList, passphrase);
```

Note: it will work for words off of the word list, but it allows use of secure CharSequences if they match the wordlist, normalized or not (as they are never `toString`ed)

Those examples both use SpongyCastle, if you don't need or want that dependency, you can use `javax.crypto` like so:

```java
byte[] seed = new SeedCalculator(JavaxPBKDF2WithHmacSHA512.INSTANCE).calculateSeed(mnemonic, passphrase);
```

That will not work on Android API < 26 https://developer.android.com/reference/javax/crypto/SecretKeyFactory.html and see Issue #17.

