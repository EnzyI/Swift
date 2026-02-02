package com.swift.core.simd;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

public class SwiftMath {
    // SPECIES tự động chọn độ dài vector tối ưu cho CPU (256-bit hoặc 512-bit)
    private static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

    /**
     * Tính toán khoảng cách bình phương cho hàng loạt thực thể cùng lúc.
     * Nhanh gấp nhiều lần so với vòng lặp for thông thường.
     */
    public static void computeDistances(float[] x1, float[] y1, float[] z1, 
                                        float[] x2, float[] y2, float[] z2, 
                                        float[] results) {
        int i = 0;
        int upperBound = SPECIES.loopBound(x1.length);

        // Vòng lặp SIMD: Xử lý nhiều phần tử mỗi lần (ví dụ: 8 số float)
        for (; i < upperBound; i += SPECIES.length()) {
            var vx = FloatVector.fromArray(SPECIES, x1, i).sub(FloatVector.fromArray(SPECIES, x2, i));
            var vy = FloatVector.fromArray(SPECIES, y1, i).sub(FloatVector.fromArray(SPECIES, y2, i));
            var vz = FloatVector.fromArray(SPECIES, z1, i).sub(FloatVector.fromArray(SPECIES, z2, i));
            
            // (x1-x2)^2 + (y1-y2)^2 + (z1-z2)^2
            var vDistSq = vx.mul(vx).add(vy.mul(vy)).add(vz.mul(vz));
            vDistSq.intoArray(results, i);
        }

        // Xử lý các phần tử còn dư (nếu danh sách không chia hết cho độ dài vector)
        for (; i < x1.length; i++) {
            float dx = x1[i] - x2[i];
            float dy = y1[i] - y2[i];
            float dz = z1[i] - z2[i];
            results[i] = dx * dx + dy * dy + dz * dz;
        }
    }
}

