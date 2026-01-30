public class HillCipher {

    static int[][] keyMat = {
            {1, 2, 1},
            {2, 3, 2},
            {2, 2, 1}
    };

    static int[][] invKeyMat = {
            {-1, 0, 1},
            { 2,-1, 0},
            {-2, 2,-1}
    };

    static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Encrypt 3 characters
    static String encode(char a, char b, char c) {
        int posa = a - 'A';
        int posb = b - 'A';
        int posc = c - 'A';

        int x = posa * keyMat[0][0] + posb * keyMat[1][0] + posc * keyMat[2][0];
        int y = posa * keyMat[0][1] + posb * keyMat[1][1] + posc * keyMat[2][1];
        int z = posa * keyMat[0][2] + posb * keyMat[1][2] + posc * keyMat[2][2];

        return "" +
                ALPHABET.charAt(x % 26) +
                ALPHABET.charAt(y % 26) +
                ALPHABET.charAt(z % 26);
    }

    // Decrypt 3 characters
    static String decode(char a, char b, char c) {
        int posa = a - 'A';
        int posb = b - 'A';
        int posc = c - 'A';

        int x = posa * invKeyMat[0][0] + posb * invKeyMat[1][0] + posc * invKeyMat[2][0];
        int y = posa * invKeyMat[0][1] + posb * invKeyMat[1][1] + posc * invKeyMat[2][1];
        int z = posa * invKeyMat[0][2] + posb * invKeyMat[1][2] + posc * invKeyMat[2][2];

        return "" +
                ALPHABET.charAt((x % 26 + 26) % 26) +
                ALPHABET.charAt((y % 26 + 26) % 26) +
                ALPHABET.charAt((z % 26 + 26) % 26);
    }

    public static void main(String[] args) {

        String msg = "SecurityLaboratory";
        StringBuilder enc = new StringBuilder();
        StringBuilder dec = new StringBuilder();

        System.out.println("Simulation of Hill Cipher");
        System.out.println("Input message : " + msg);

        msg = msg.toUpperCase().replaceAll("\\s+", "");

        // Padding with X
        int n = msg.length() % 3;
        if (n != 0) {
            for (int i = 0; i < 3 - n; i++)
                msg += "X";
        }

        System.out.println("Padded message : " + msg);

        // Encryption
        for (int i = 0; i < msg.length(); i += 3) {
            enc.append(encode(
                    msg.charAt(i),
                    msg.charAt(i + 1),
                    msg.charAt(i + 2)
            ));
        }

        System.out.println("Encoded message : " + enc);

        // Decryption
        for (int i = 0; i < enc.length(); i += 3) {
            dec.append(decode(
                    enc.charAt(i),
                    enc.charAt(i + 1),
                    enc.charAt(i + 2)
            ));
        }

        System.out.println("Decoded message : " + dec);
    }
}

