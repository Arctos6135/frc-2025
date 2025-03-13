package frc.robot.constants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import java.util.Arrays;
import java.util.List;

public class PositionConstants {
  public static double CENTER_TO_EDGE_REEF = 1.3;
  public static double reefClearance = 0.5;

  public static Translation2d BLUE_REEF = new Translation2d(4.500, 4.025);
  public static Translation2d RED_REEF = new Translation2d(13.050, 4.025);

  public static Translation2d A = new Translation2d(1.3, 0);
  public static Translation2d B = new Translation2d(0.65, -1.1258);
  public static Translation2d C = new Translation2d(-0.65, -1.1258);
  public static Translation2d D = new Translation2d(-1.3, 0);
  public static Translation2d E = new Translation2d(-0.65, 1.1258);
  public static Translation2d F = new Translation2d(0.65, 1.1258);

  public static Translation2d[] RED_SCORING_ZONES = {
    RED_REEF.plus(A),
    RED_REEF.plus(B),
    RED_REEF.plus(C),
    RED_REEF.plus(D),
    RED_REEF.plus(E),
    RED_REEF.plus(F),
  };

  public static Translation2d[] BLUE_SCORING_ZONES = {
    BLUE_REEF.plus(A),
    BLUE_REEF.plus(B),
    BLUE_REEF.plus(C),
    BLUE_REEF.plus(D),
    BLUE_REEF.plus(E),
    BLUE_REEF.plus(F),
  };

  public static List<Pose2d> RED_SCORING_POSES =
      Arrays.asList(
          new Pose2d[] {
            new Pose2d(RED_SCORING_ZONES[0], Rotation2d.fromDegrees(180)),
            new Pose2d(RED_SCORING_ZONES[1], Rotation2d.fromDegrees(120)),
            new Pose2d(RED_SCORING_ZONES[2], Rotation2d.fromDegrees(60)),
            new Pose2d(RED_SCORING_ZONES[3], Rotation2d.fromDegrees(0)),
            new Pose2d(RED_SCORING_ZONES[4], Rotation2d.fromDegrees(-60)),
            new Pose2d(RED_SCORING_ZONES[5], Rotation2d.fromDegrees(-120)),
          });

  public static List<Pose2d> BLUE_SCORING_POSES =
      Arrays.asList(
          new Pose2d[] {
            new Pose2d(BLUE_SCORING_ZONES[0], Rotation2d.fromDegrees(180)),
            new Pose2d(BLUE_SCORING_ZONES[1], Rotation2d.fromDegrees(120)),
            new Pose2d(BLUE_SCORING_ZONES[2], Rotation2d.fromDegrees(60)),
            new Pose2d(BLUE_SCORING_ZONES[3], Rotation2d.fromDegrees(0)),
            new Pose2d(BLUE_SCORING_ZONES[4], Rotation2d.fromDegrees(-60)),
            new Pose2d(BLUE_SCORING_ZONES[5], Rotation2d.fromDegrees(-120)),
          });
}
