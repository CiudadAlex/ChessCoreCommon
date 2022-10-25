package org.leviatan.chess.engine.deeplearning;

import java.util.List;
import java.util.function.Function;

import org.leviatan.chess.tools.platform.KeyDoubleBean;
import org.leviatan.chess.tools.platform.MapObjectToDouble;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

/**
 * DeepLearningUtils.
 *
 * @author Alejandro
 *
 */
public final class DeepLearningUtils {

    private DeepLearningUtils() {
    }

    /**
     * Devuelve el indice máximo.
     *
     * @param byteArr
     *            byteArr
     * @return el indice máximo
     */
    public static int getArgMax(final double[] byteArr) {
        return getArgMaxUntilLength(byteArr, byteArr.length);
    }

    /**
     * Devuelve el indice máximo hasta la longitud dada.
     *
     * @param byteArr
     *            byteArr
     * @param length
     *            length
     * @return el indice máximo
     */
    public static int getArgMaxUntilLength(final double[] byteArr, final int length) {

        double maxItem = Double.NEGATIVE_INFINITY;
        int maxIndex = -1;

        for (int i = 0; i < length; i++) {

            final double item = byteArr[i];
            if (item > maxItem) {
                maxItem = item;
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    /**
     * Devuelve la lista de índices desde el que tiene el valor máximo al que
     * tiene el valor mínimo.
     *
     * @param byteArr
     *            byteArr
     * @return la lista de índices desde el que tiene el valor máximo al que
     *         tiene el valor mínimo
     */
    public static List<KeyDoubleBean<Integer>> getListArgMaxToMin(final double[] byteArr) {
        return getListArgMaxToMinUntilLength(byteArr, byteArr.length);
    }

    /**
     * Devuelve la lista de índices desde el que tiene el valor máximo al que
     * tiene el valor mínimo.
     *
     * @param byteArr
     *            byteArr
     * @param length
     *            length
     * @return la lista de índices desde el que tiene el valor máximo al que
     *         tiene el valor mínimo
     */
    public static List<KeyDoubleBean<Integer>> getListArgMaxToMinUntilLength(final double[] byteArr, final int length) {
        return getListArgMaxToMinFromOffsetUntilLength(byteArr, 0, length);
    }

    /**
     * Devuelve la lista de índices desde el que tiene el valor máximo al que
     * tiene el valor mínimo.
     *
     * @param byteArr
     *            byteArr
     * @param offset
     *            offset
     * @param length
     *            length
     * @return la lista de índices desde el que tiene el valor máximo al que
     *         tiene el valor mínimo
     */
    public static List<KeyDoubleBean<Integer>> getListArgMaxToMinFromOffsetUntilLength(final double[] byteArr, final int offset,
            final int length) {

        final MapObjectToDouble<Integer> map = new MapObjectToDouble<Integer>();

        for (int i = offset; i < length; i++) {

            final double value = byteArr[i];
            map.addToValue(i, value);
        }

        final List<KeyDoubleBean<Integer>> list = map.getListOrdered(false);

        return list;
    }

    /**
     * Prints the array.
     *
     * @param array
     *            array
     */
    public static void printArray(final double[] array) {

        final StringBuilder sb = new StringBuilder("[");

        boolean firstIteration = true;

        for (final double d : array) {

            if (!firstIteration) {
                sb.append(", ");
            }

            sb.append(d);

            firstIteration = false;
        }

        sb.append("]");

        System.out.println(sb.toString());
    }

    /**
     * Returns an INDArray.
     *
     * @param array
     *            array
     * @return an INDArray
     */
    public static INDArray getINDArray(final double[] array) {

        final INDArray indArray = Nd4j.create(new int[] { 1, array.length }, 'f');

        for (int j = 0; j < array.length; j++) {
            indArray.putScalar(new int[] { 0, j }, array[j]);
        }

        return indArray;
    }

    /**
     * Builds the dataset.
     *
     * @param <T>
     *            object type
     * @param listLearningUnits
     *            listLearningUnits
     * @param extractorInput
     *            extractorInput
     * @param extractorOutput
     *            extractorOutput
     * @param numInputs
     *            numInputs
     * @param numOutputs
     *            numOutputs
     * @return the dataset
     */
    public static <T> DataSet buildDataSet(final List<T> listLearningUnits, final Function<T, double[]> extractorInput,
            final Function<T, double[]> extractorOutput, final int numInputs, final int numOutputs) {

        final int batchSize = listLearningUnits.size();

        // dimension 0 = number of examples in batch
        // dimension 1 = size of each vector (i.e., number of characters)
        final INDArray input = Nd4j.create(new int[] { batchSize, numInputs }, 'f');
        final INDArray labels = Nd4j.create(new int[] { batchSize, numOutputs }, 'f');

        for (int i = 0; i < batchSize; i++) {

            final T learningUnit = listLearningUnits.get(i);
            final double[] inputData = extractorInput.apply(learningUnit);
            final double[] outputData = extractorOutput.apply(learningUnit);

            // AppLogger.logData("inputData", inputData);
            // AppLogger.logData("outputData", outputData);

            // input
            for (int j = 0; j < inputData.length; j++) {
                input.putScalar(new int[] { i, j }, inputData[j]);
            }

            for (int j = 0; j < outputData.length; j++) {
                labels.putScalar(new int[] { i, j }, outputData[j]);
            }
        }

        return new DataSet(input, labels);
    }
}
