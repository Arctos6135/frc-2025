package frc.robot.utils;

import java.util.Random;

public class GaussianNoiseGenerator {
  private double noiseStd;
  private Random rand;

  public GaussianNoiseGenerator(double noiseStd) {
    this.noiseStd = noiseStd;
    this.rand = new Random();
  }

  public double get() {
    return rand.nextGaussian() * noiseStd;
  }
}
