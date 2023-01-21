// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.cameraserver.CameraServer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
*/

public class Robot extends TimedRobot {
  
 // these motors are for the wheels
  private CANSparkMax motorLeftFront = new CANSparkMax(1, MotorType.kBrushless);
  private CANSparkMax motorLeftBack = new CANSparkMax(2, MotorType.kBrushless);
  private CANSparkMax motorRightFront = new CANSparkMax(3, MotorType.kBrushless);
  private CANSparkMax motorRightBack = new CANSparkMax(4, MotorType.kBrushless);
  
  private DifferentialDrive drive = new DifferentialDrive(motorLeftFront,motorRightFront);
  private Joystick joyWheels = new Joystick(0);
  private Double MaxTurn = 0.0;
  private Double MaxDrivePower = 0.0;
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
  */
  @Override
  public void robotInit() {
    CameraServer.startAutomaticCapture();

    motorLeftBack.setInverted(true);
    motorRightFront.setInverted(true);
    // the back motors follow the front motors (inversely)
    motorLeftBack.follow(motorLeftFront);
    motorRightBack.follow(motorRightFront);

    // dead band prevents joystick drift
    drive.setDeadband(0.05);
  }

  @Override
  public void robotPeriodic() {

  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    // use the joystick to drive
    // for some reason, the Y axis is 1st; STRANGE **
    double drivePower = -joyWheels.getRawAxis(0 /*Y axis*/);
    double turn = -joyWheels.getRawAxis(1 /*X axis*/);

    //power and turn are switched
    drive.arcadeDrive(drivePower * MaxDrivePower, turn * MaxTurn);

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
