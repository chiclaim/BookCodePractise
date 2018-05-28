package com.chiclaim.data.structure.leetcode.queue;

import java.util.*;

public class LeetCode347 {

    class Solution {
        class Freq implements Comparable<Freq> {
            int value, freq;

            public Freq(int value, int freq) {
                this.value = value;
                this.freq = freq;
            }

            @Override
            public int compareTo(Freq o) {
                return this.freq - o.freq;
            }
        }

        public List<Integer> topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>(nums.length);
            for (int value : nums) {
                if (map.containsKey(value)) {
                    map.put(value, map.get(value) + 1);
                } else {
                    map.put(value, 1);
                }
            }

            PriorityQueue<Freq> priorityQueue = new PriorityQueue<>();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                //先把k个元素放进优先队列中
                if (priorityQueue.size() < k) {
                    priorityQueue.add(new Freq(entry.getKey(), entry.getValue()));
                }
                //如果比队列中最小频次大
                else if (entry.getValue() > priorityQueue.peek().freq) {
                    priorityQueue.remove();
                    priorityQueue.add(new Freq(entry.getKey(), entry.getValue()));
                }
            }

            List<Integer> result = new ArrayList<>(k);
            while (!priorityQueue.isEmpty()) {
                result.add(priorityQueue.remove().value);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LeetCode347().new Solution();
        int[] original = new int[]{10, 22, 11, 11, 66, 4, 4, 4, 0, 1, 2, 3, 3, 3, 3};
        List<Integer> result = solution.topKFrequent(original, 3);
        System.out.println(result);
    }
}