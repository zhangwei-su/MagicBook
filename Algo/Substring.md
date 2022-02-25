# KMP
[[Dynamic Programming#Same prefix-suffix KMPNext]]
Arrays.fill(charMap, -1);->record most right index of each char in pattern.
when mismatch, check the char after pattern cover (nextI).
if it NOT appear in pattern, start new matching from nextI+1;
if it appear in pattern, align pattern at this char. Recal i, rematch P from begin

