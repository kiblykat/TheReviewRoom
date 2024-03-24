package sg.com.smartinventory.utility;

public class Utility {
    /**
     * Get the method name of the caller.
     * <p>
     * Java 9 introduced the Stack-Walking API to traverse the JVM stack frames in a
     * lazy and efficient manner.
     * First, we get a StackWalker instance using the getInstance() factory method.
     * Then we use the walk() method to traverse the stack frames from the top to
     * the bottom:
     * <p>
     * <ul>
     * <li>The walk() method can convert a stream of stack frames —
     * Stream<StackFrame> — to anything.
     * <li>The first element in the given stream is the top frame on the stack.
     * <li>The top frame on the stack always represents the currently executing
     * method.
     * </ul>
     * <p>
     * Therefore, if we get the first element from the stream, we’ll know the
     * currently executing method details. More specifically, we can use
     * StackFrame.getMethodName() to find the method name.
     * <p>
     * Compared to other approaches (more on them later), the Stack-Walking API has
     * a few advantages:
     * <p>
     * <ul>
     * <ul>
     * No need to create a dummy anonymous inner class instance — new
     * Object().getClass() {}
     * <ul>
     * No need to create a dummy exception — new Throwable()
     * <ul>
     * No need to eagerly capture the whole stacktrace, which can be costly
     * <p>
     * <ul>
     * On the contrary, the StackWalker only walks the stack one by one in a lazy
     * fashion. In this case, it only fetches the top frame, as opposed to the
     * stacktrace approach which captures all the frames eagerly.
     * 
     * @param levelsUp The number of levels up the stack trace.
     * 
     * @return The method name of the caller.
     */
    public static String getMethodName(int levelsUp) {
        // Check if the levelsUp is less than 0. If it is, throw an exception.
        if (levelsUp < 0) {
            throw new IllegalArgumentException("levelsUp must be greater than or equal to 0. ");
        }

        // Get the method name of the caller. The top is this function name itself, so
        // it should be skipped.
        return StackWalker.getInstance()
                .walk(s -> s.skip(1 + levelsUp).findFirst())
                .get()
                .getMethodName();
    }

    /**
     * Get the current method name.
     * 
     * @return The current method name.
     * 
     * @see #getMethodName()
     */
    public static String getCurrentMethodName() {
        // Get the method name of the caller. You need to skip 1 level up to get the
        // current method name as you are calling a base function below it.
        return getMethodName(1);
    }

    /**
     * Get the caller method name.
     * 
     * @return The caller method name.
     * 
     * @see #getMethodName()
     */
    public static String getCallerMethodName() {
        // Get the method name of the caller. You need to skip 2 level up to get the
        // current method name as you are calling a base function below it.
        return getMethodName(2);
    }

    /**
     * Get the caller method name.
     * 
     * @param levelsUp The number of levels up the stack trace.
     * 
     * @return The caller method name.
     * 
     * @see #getCallerMethodName()
     */
    public static String getCallerMethodName(int levelsUp) {
        // Check if the levelsUp is less than 0. If it is, throw an exception.
        if (levelsUp < 0) {
            throw new IllegalArgumentException("levelsUp must be greater than or equal to 0. ");
        }

        // Get the method name of the caller. You need to skip 1 level up to get the
        // current method name as you are calling a base function below it.
        return getMethodName(2 + levelsUp);
    }
}