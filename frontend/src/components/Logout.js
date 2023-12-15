import React from 'react';
import { FaSignOutAlt } from 'react-icons/fa'; 

function Logout() {
    const handleLogout = () => {
        window.location.reload();
    };

    return (
        <button onClick={handleLogout} className="logout-button">
            <FaSignOutAlt /> Logout
        </button>
    );
}

export default Logout;
