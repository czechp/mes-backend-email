package com.bispol.emailservicebackendspring.mes_quality_control;

public enum ProductType {
    PTS {
        @Override
        public String getName() {
            return "PTS";
        }
    },
    CANDLE {
        @Override
        public String getName() {
            return "Świeczka";
        }
    },
    TEALIGHT {
        @Override
        public String getName() {
            return "TeaLight";
        }
    };

    public abstract String getName();
}
