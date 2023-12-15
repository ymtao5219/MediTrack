import React, { useState, useEffect } from 'react';
import axios from 'axios';

function DoctorMedicalRecords({ doctorId }) {
    const [records, setRecords] = useState([]);
    const [editingRecord, setEditingRecord] = useState(null);

    useEffect(() => {
        fetchRecords();
    }, [doctorId]);

    const fetchRecords = () => {
        // Fetch medical records logic
        axios.get(`http://localhost:8080/medicalrecords/doctors/${doctorId}`)
            .then(response => setRecords(response.data))
            .catch(error => console.error('Error fetching records:', error));
    };

    const handleEdit = (record) => {
        setEditingRecord(record);
    };

    const handleUpdate = (updatedRecord) => {
        // Using doctorId directly from the record
        axios.put(`http://localhost:8080/medicalrecords/patients/${updatedRecord.patientId}/${updatedRecord.recordId}`, updatedRecord)
            .then(() => {
                fetchRecords();
                setEditingRecord(null);
            })
            .catch(error => console.error('Error updating record:', error));
    };

    const handleDelete = (recordId, doctorId) => {
        // Using doctorId for the delete endpoint
        axios.delete(`http://localhost:8080/medicalrecords/doctors/${doctorId}/${recordId}`)
            .then(() => fetchRecords())
            .catch(error => console.error('Error deleting record:', error));
    };

    return (
        <div>
            <h2>Medical Records</h2>
            {editingRecord ? (
                <EditRecordForm record={editingRecord} onUpdate={handleUpdate} onCancel={() => setEditingRecord(null)} />
            ) : (
                <ul>
                    {records.map(record => (
                        <li key={record.recordId}>
                            <p>Type: {record.recordType}</p>
                            <p>Description: {record.recordDescription}</p>
                            <button onClick={() => handleEdit(record)}>Edit</button>
                            <button onClick={() => handleDelete(record.recordId, record.doctorId)}>Delete</button>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

function EditRecordForm({ record, onUpdate, onCancel }) {
    const [type, setType] = useState(record.recordType);
    const [description, setDescription] = useState(record.recordDescription);

    const handleSubmit = (e) => {
        e.preventDefault();
        onUpdate({ ...record, recordType: type, recordDescription: description });
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Record Type:
                <input type="text" value={type} onChange={(e) => setType(e.target.value)} />
            </label>
            <label>
                Description:
                <textarea value={description} onChange={(e) => setDescription(e.target.value)} />
            </label>
            <button type="submit">Update</button>
            <button type="button" onClick={onCancel}>Cancel</button>
        </form>
    );
}

export default DoctorMedicalRecords;
