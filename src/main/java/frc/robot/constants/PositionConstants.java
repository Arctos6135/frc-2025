package frc.robot.constants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import java.util.Arrays;
import java.util.List;

public class PositionConstants {
  public static double CENTER_TO_EDGE_REEF = 1.0;
  public static double reefClearance = 0.01;

  public static Translation2d BLUE_REEF = new Translation2d(4.500, 4.025);
  public static Translation2d RED_REEF = new Translation2d(13.050, 4.025);

  public static Translation2d A = new Translation2d(CENTER_TO_EDGE_REEF, 0);
  public static Translation2d B =
      new Translation2d(
          Math.sin(Math.PI / 6) * CENTER_TO_EDGE_REEF, Math.cos(Math.PI / 6) * CENTER_TO_EDGE_REEF);
  public static Translation2d C =
      new Translation2d(
          -Math.sin(Math.PI / 6) * CENTER_TO_EDGE_REEF,
          Math.cos(Math.PI / 6) * CENTER_TO_EDGE_REEF);
  public static Translation2d D = new Translation2d(-CENTER_TO_EDGE_REEF, 0);
  public static Translation2d E =
      new Translation2d(
          -Math.sin(Math.PI / 6) * CENTER_TO_EDGE_REEF,
          -Math.cos(Math.PI / 6) * CENTER_TO_EDGE_REEF);
  public static Translation2d F =
      new Translation2d(
          Math.sin(Math.PI / 6) * CENTER_TO_EDGE_REEF,
          -Math.cos(Math.PI / 6) * CENTER_TO_EDGE_REEF);

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
            new Pose2d(BLUE_SCORING_ZONES[1], Rotation2d.fromDegrees(240)),
            new Pose2d(BLUE_SCORING_ZONES[2], Rotation2d.fromDegrees(300)),
            new Pose2d(BLUE_SCORING_ZONES[3], Rotation2d.fromDegrees(0)),
            new Pose2d(BLUE_SCORING_ZONES[4], Rotation2d.fromDegrees(60)),
            new Pose2d(BLUE_SCORING_ZONES[5], Rotation2d.fromDegrees(120)),
          });

  // All values from the game manual (in meters).  If we need to change change the pose2ds not
  // these.
  public static final double FIELD_HEIGHT = 8.05;
  public static final double MIDDLE_Y = FIELD_HEIGHT / 2.0;
  public static final double MIDDLE_TO_OUTSIDE_CAGE = 3.24;
  public static final double BARGE_Y = MIDDLE_Y + MIDDLE_TO_OUTSIDE_CAGE;
  public static final double PROCESSOR_Y = MIDDLE_Y - MIDDLE_TO_OUTSIDE_CAGE;
  public static final double MIDDLE_LINE_X = 8.875;
  public static final double START_LINE_X = 3.6576 + 1.66 + 2.24;
  // public static final double MIDDLE_TO_START_LINE = 1.175;
  public static final double MIDDLE_TO_START_LINE = MIDDLE_LINE_X - START_LINE_X;
  public static final double BLUE_X = MIDDLE_LINE_X - MIDDLE_TO_START_LINE;
  public static final double RED_X = MIDDLE_LINE_X + MIDDLE_TO_START_LINE;

  public static final Pose2d BLUE_BARGE = new Pose2d(BLUE_X, BARGE_Y, Rotation2d.fromDegrees(180));
  public static final Pose2d BLUE_MIDDLE =
      new Pose2d(BLUE_X, MIDDLE_Y, Rotation2d.fromDegrees(180));
  public static final Pose2d BLUE_PROCESSOR =
      new Pose2d(BLUE_X, PROCESSOR_Y, Rotation2d.fromDegrees(180));
  public static final Pose2d RED_BARGE = new Pose2d(RED_X, BARGE_Y, Rotation2d.fromDegrees(0));
  public static final Pose2d RED_MIDDLE = new Pose2d(RED_X, MIDDLE_Y, Rotation2d.fromDegrees(0));
  public static final Pose2d RED_PROCESSOR =
      new Pose2d(RED_X, PROCESSOR_Y, Rotation2d.fromDegrees(0));
}
