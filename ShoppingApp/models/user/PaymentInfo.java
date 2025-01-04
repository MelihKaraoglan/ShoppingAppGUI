package models.user;

    // PaymentInfo Class: Ödeme bilgileri
    public class PaymentInfo {
        private String cardNumber;
        private String expirationDate;
        private String cvv;
    
        public PaymentInfo(String cardNumber, String expirationDate, String cvv) {
            this.cardNumber = cardNumber;
            this.expirationDate = expirationDate;
            this.cvv = cvv;
        }
    
        public String getCardNumber() {
            return cardNumber;
        }
    
        public String getExpirationDate() {
            return expirationDate;
        }
    
        public String getCvv() {
            return cvv;
        }
    
        @Override
        public String toString() {
            return "Card Number: " + cardNumber + ", Expiration Date: " + expirationDate;
        }
    }