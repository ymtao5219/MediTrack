import React from 'react';

function DoctorInfo({ data }) {
    return (
        <ul>
            <li>First Name: {data.firstName}</li>
            <li>Last Name: {data.lastName}</li>
            <li>Specialization: {data.specialization}</li>
            <li>Contact Number: {data.contactNumber}</li>
            <li>Email Address: {data.emailAddress}</li>
        </ul>
    );
}

export default DoctorInfo;
