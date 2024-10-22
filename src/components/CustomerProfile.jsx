import React, { useState, useEffect } from 'react';
import { getCustomer } from '../services/api';

function CustomerProfile() {
  const [customer, setCustomer] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchCustomer = async () => {
      const customerId = localStorage.getItem('customerId');
      if (customerId) {
        try {
          const response = await getCustomer(customerId);
          setCustomer(response.data);
        } catch (err) {
          setError('Error fetching customer data. Please try again later.');
          console.error('Error fetching customer data:', err.message);
        }
      }
    };

    fetchCustomer();
  }, []);

  if (error) return <div>{error}</div>;
  if (!customer) return <div>Loading...</div>;

  return (
    <div>
      <h1>Customer Profile</h1>
      <p>Name: {customer.name}</p>
      <p>Email: {customer.email}</p>
    </div>
  );
}

export default CustomerProfile;