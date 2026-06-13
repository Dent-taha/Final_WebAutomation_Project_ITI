package com.project.drivers;

public enum Browsers {

    EDGE {
        @Override
        public AbstractDriver getDriverFactory() {
            return  new EdgeFactory();
        }
    },
    CHROME {
        @Override
        public AbstractDriver getDriverFactory() {
            return new ChromeFactory();
        }
    };

    public abstract AbstractDriver getDriverFactory();
}
