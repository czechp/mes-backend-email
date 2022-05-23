package com.bispol.emailservicebackendspring.mes_quality_control.dto;

public enum UserRole {
    QUALITY_CONTROL {
        @Override
        public String getName() {
            return "Kontrola jakości";
        }
    },
    PRODUCTION {
        @Override
        public String getName() {
            return "Produkcja";
        }
    },
    NOT_EXISTS {
        @Override
        public String getName() {
            return "Nie rozpoznano";
        }
    },
    MAINTENANCE {
        @Override
        public String getName() {
            return "Utrzymanie ruchu";
        }
    };

    public abstract String getName();
}