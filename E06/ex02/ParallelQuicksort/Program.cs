using System;

namespace ParallelQuickSort {
    public class Program {
        public static void Main() {
            const int N = 10000000;
            var numberArray = new decimal[N];
            Console.WriteLine("Nof processors: {0}", Environment.ProcessorCount);
            int threshold;
            do {
                ResetRandomArray(numberArray);
                Console.Write("Enter threshold: ");
                int.TryParse(Console.ReadLine(), out threshold);
                if (threshold > 0) {
                    DateTime start = DateTime.Now;
                    new ParallelQuickSort(threshold).Sort(numberArray);
                    DateTime stop = DateTime.Now;
                    Console.WriteLine("Total sort time: {0} ms", (stop - start).TotalMilliseconds);
                }
            } while (threshold > 0);
        }

        private static void ResetRandomArray(decimal[] numberArray) {
            var random = new Random(4711);
            for (int i = 0; i < numberArray.Length; i++) {
                numberArray[i] = random.Next();
                numberArray[i] *= random.Next();
                numberArray[i] *= random.Next();
            }
        }
    }
}
