import React from 'react';

function PatientInfo({ data }) {
    return (
        <ul>
            <li>First Name: {data.firstName}</li>
            <li>Last Name: {data.lastName}</li>
            <li>Date of Birth: {data.dateOfBirth}</li>
            <li>Gender: {data.gender}</li>
            <li>Address: {data.address}</li>
            <li>Contact Number: {data.contactNumber}</li>
        </ul>
    );
}

export default PatientInfo;
