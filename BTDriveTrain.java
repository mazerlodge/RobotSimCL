public class BTDriveTrain {

    public BTDriveTrain() {

    }

    public void update(ControlBoard cb) {

	// Simple retrieval example
	System.out.printf("DT got cb.x=%d\n", cb.xVal);

	// Reading info out of DriveInfo structure/class.
	DriveInfo leftDI = cb.getDriveInfo(DriveSide.LEFT);
	DriveInfo rightDI = cb.getDriveInfo(DriveSide.RIGHT);
	System.out.printf("DT got leftDuration=%d\n", leftDI.duration);
        System.out.printf("DT got rightDuration=%d\n", rightDI.duration);

	// DriveTrain is responsible for decrementing durations
	if (leftDI.duration > 0)
	    leftDI.duration--;

	if (rightDI.duration > 0)
	    rightDI.duration--;
	
	// Post-decrement output
	System.out.printf("DT POST got leftDuration=%d\n", leftDI.duration);
	cb.setDriveInfo(leftDI, DriveSide.LEFT);
	cb.setDriveInfo(rightDI, DriveSide.RIGHT);
	      
    }

}
