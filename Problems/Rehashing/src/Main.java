import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static class TableEntry<T> {
        private final int key;
        private final T value;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }
    }

    private static class HashTable<T> {
        private int size;
        private TableEntry[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            int idx = findKey(key);

            if (idx == -1) {
                this.rehash();
                return  put( key, value);
            }

            table[idx] = new TableEntry(key, value);
            return true;
        }

        public T get(int key) {
            int idx = findKey(key);

            if (idx == -1 || table[idx] == null) {
                return null;
            }

            return (T) table[idx].getValue();          
        }

        private int findKey(int key) {
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        private void rehash() {
            TableEntry[] oldTable = new TableEntry[size];
            for (int i = 0; i < size; i++) {
                oldTable[i] = this.table[i];
            }
            this.size *= 2;
            this.table = new TableEntry[size];
            for (TableEntry elt : oldTable) {
                put(elt.getKey(), (T) elt.getValue());
            }
        }

        @Override
        public String toString() {
            StringBuilder tableStringBuilder = new StringBuilder();

            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) {
                    tableStringBuilder.append(i + ": null");
                } else {
                    tableStringBuilder.append(i + ": key=" + table[i].getKey()
                                                + ", value=" + table[i].getValue());
                }

                if (i < table.length - 1) {
                    tableStringBuilder.append("\n");
                }
            }

            return tableStringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        int n = sc.nextInt();
        HashTable<String> hashTable = new HashTable(5);
        for(int i=0; i<n; i++){
            int key = sc.nextInt();
            String val = sc.next();
            hashTable.put( key, val);
        }
        System.out.println(hashTable);
    }
}