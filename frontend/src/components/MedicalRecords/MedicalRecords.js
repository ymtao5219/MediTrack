import React from 'react';

function MedicalRecords({ records }) {
    const recordsArray = Object.values(records); // Convert object to array
    return (
        <ul>
            {recordsArray.map((record) => (
                <li key={record.recordId}>
                    <div className="record-details">
                        <h4>Record Type: {record.recordType}</h4>
                        <p>Date of Submission: {record.dateOfSubmission}</p>
                        <p>Description: {record.recordDescription}</p>
                    </div>
                </li>
            ))}
        </ul>
    );
}

export default MedicalRecords;
