﻿using System;

namespace ParallelMatrixMultiplication {
    public class ParallelMatrixMultiplication {
        // Result = A * B, A has dimN x dimK, B has dimK x dimM, Result has dimN x dimM.
        public static long[,] Multiply(long[,] matrixA, long[,] matrixB) {
            var dimN = matrixA.GetLength(0);
            var dimK = matrixA.GetLength(1);
            if (dimK != matrixB.GetLength(0)) {
                throw new ArgumentException("Dimensions for matrix multiplication do not match");
            }
            var dimM = matrixB.GetLength(1);
            var matrixC = new long[dimN, dimM];
            // Computation
            for (var i = 0; i < dimN; i++) {
                for (var j = 0; j < dimM; j++) {
                    matrixC[i, j] = 0;
                    for (var k = 0; k < dimK; k++) {
                        matrixC[i, j] += matrixA[i, k] * matrixB[k, j];
                    }
                }
            }
            return matrixC;
        }
    }
}
