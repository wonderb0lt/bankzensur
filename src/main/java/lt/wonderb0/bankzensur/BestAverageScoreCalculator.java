package lt.wonderb0.bankzensur;

public interface BestAverageScoreCalculator {
    /**
     * @param averages The averages as read from a source. First dimension are the rows of scores. Second dimension is a
     *                 tuple of name and score (String representation of an Integer). One person can have multiple
     *                 scores. There can be no rows. The tuples are guaranteed to always be in "name,score" form.
     * @return The best average score. If the calculated average is not an integer, the value should be floored. If no
     *         values are given, zero is returned.
     */
    Integer calculateBestAverage(String[][] averages);
}
