package uk.org.lidalia.slf4jtest;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * A <a href="https://github.com/junit-team/junit/wiki">JUnit</a> rule that clears the ThreadLocal state of all the TestLoggers
 * and the TestLoggerFactory.
 */
public class TestLoggerFactoryResetRule implements TestRule {

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new TestLoggerFactoryResettingStatement(base);
    }

    private static class TestLoggerFactoryResettingStatement extends Statement {
        private final Statement base;

        public TestLoggerFactoryResettingStatement(Statement base) {
            this.base = base;
        }

        @Override
        public void evaluate() throws Throwable {
            try {
                base.evaluate();
            } finally {
                TestLoggerFactory.clear();
            }
        }
    }
}
