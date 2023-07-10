package com.fx.w2p.parts;

import com.fx.w2p.pojo.IntVector;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author pscha
 */
public class PartFaceIndices {

    private final List<String> list;
    private final int beforeMax;
    private int max = 0;

    public PartFaceIndices(List<String> list, int beforeMax) {
        this.list = list;
        this.beforeMax = beforeMax;
    }

    public String getString() {
        String erg = "face_indices {\n";
        long size = list.stream().filter(str -> str.split(" ")[0].equalsIgnoreCase("f")).count();
        erg += size + ",\n";

        List<String> vertexList = list.stream().filter(str -> str.split(" ")[0].equalsIgnoreCase("f")).collect(Collectors.toList());
        List<IntVector> vectorList = new ArrayList<>();

        for (String line : vertexList) {
            String parts[] = line.split(" ");

            if (parts[1].split("/").length - 1 > 1 || parts[1].split("/").length == 2) {

                String face1[] = parts[1].split("/");
                String face2[] = parts[2].split("/");
                String face3[] = parts[3].split("/");

                int x = Integer.valueOf(face1[0]);
                int y = Integer.valueOf(face2[0]);
                int z = Integer.valueOf(face3[0]);

                vectorList.add(new IntVector(x - 1 - beforeMax, y - 1 - beforeMax, z - 1 - beforeMax));
            } else {

                int x = Integer.valueOf(parts[1]);
                int y = Integer.valueOf(parts[2]);
                int z = Integer.valueOf(parts[3]);

                vectorList.add(new IntVector(x - 1 - beforeMax, y - 1 - beforeMax, z - 1 - beforeMax));
            }
        }

        for (int i = 0; i < vectorList.size(); i++) {
            IntVector intVector = vectorList.get(i);

            if (intVector.getX() > max) {
                max = intVector.getX();
            }
            if (intVector.getY() > max) {
                max = intVector.getY();
            }
            if (intVector.getZ() > max) {
                max = intVector.getZ();
            }

            erg += intVector.toString();
            if (i < vectorList.size() - 1) {
                erg += ",";
            }
            if ((i + 1) % 2 == 0) {
                erg += "\n";
            }

        }

        erg += "}\n";
        return erg;
    }

    public int getMax() {
        return max;
    }
}
