class Solution {

    String s;
    int index;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        index = 0;

        Set<String> set = parse();

        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);

        return ans;
    }

    // Handles union: a,b,c
    private Set<String> parse() {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        while (index < s.length() && s.charAt(index) != '}') {

            char ch = s.charAt(index);

            if (ch == ',') {
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                index++;
            } 
            else {
                Set<String> part = parsePart();

                Set<String> next = new HashSet<>();

                for (String a : current) {
                    for (String b : part) {
                        next.add(a + b);
                    }
                }

                current = next;
            }
        }

        result.addAll(current);

        return result;
    }

    // Handles one part: letter OR {expression}
    private Set<String> parsePart() {

        Set<String> result = new HashSet<>();

        if (s.charAt(index) == '{') {

            index++; // skip {

            result = parse();

            index++; // skip }

        } 
        else {

            StringBuilder word = new StringBuilder();

            while (index < s.length()
                    && Character.isLetter(s.charAt(index))) {

                word.append(s.charAt(index));
                index++;
            }

            result.add(word.toString());
        }

        return result;
    }
}