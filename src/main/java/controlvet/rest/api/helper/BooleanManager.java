package controlvet.rest.api.helper;

public class BooleanManager {
        private static BooleanManager instance;
        public BooleanManager() {
        }

        public static BooleanManager getInstance() {
            if (instance == null) {
                instance = new BooleanManager();
            }
            return instance;
        }

        public String parseString(Boolean value){
            return value ? "1" : "0";
        }
}
