import React, { useState, useEffect } from 'react';
import { getMenuItems, createMenuItem, updateMenuItem, deleteMenuItem } from '../services/api';

function AdminDashboard() {
  const [menuItems, setMenuItems] = useState([]);
  const [newItem, setNewItem] = useState({ name: '', description: '', price: '', category: '' });
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchMenuItems();
  }, []);

  const fetchMenuItems = async () => {
    try {
      const response = await getMenuItems();
      setMenuItems(response.data);
    } catch (err) {
      setError('Error fetching menu items');
      console.error('Error fetching menu items:', err.message);
    }
  };

  const handleCreateItem = async (e) => {
    e.preventDefault();
    try {
      await createMenuItem(newItem);
      setNewItem({ name: '', description: '', price: '', category: '' });
      fetchMenuItems();
    } catch (err) {
      setError('Error creating menu item');
      console.error('Error creating menu item:', err.message);
    }
  };

  const handleUpdateItem = async (id, updatedItem) => {
    try {
      await updateMenuItem(id, updatedItem);
      fetchMenuItems();
    } catch (err) {
      setError('Error updating menu item');
      console.error('Error updating menu item:', err.message);
    }
  };

  const handleDeleteItem = async (id) => {
    try {
      await deleteMenuItem(id);
      fetchMenuItems();
    } catch (err) {
      setError('Error deleting menu item');
      console.error('Error deleting menu item:', err.message);
    }
  };

  return (
    <div>
      <h1>Admin Dashboard</h1>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <h2>Create New Menu Item</h2>
      <form onSubmit={handleCreateItem}>
        <input
          type="text"
          placeholder="Name"
          value={newItem.name}
          onChange={(e) => setNewItem({ ...newItem, name: e.target.value })}
          required
        />
        <input
          type="text"
          placeholder="Description"
          value={newItem.description}
          onChange={(e) => setNewItem({ ...newItem, description: e.target.value })}
          required
        />
        <input
          type="number"
          placeholder="Price"
          value={newItem.price}
          onChange={(e) => setNewItem({ ...newItem, price: e.target.value })}
          required
        />
        <select
          value={newItem.category}
          onChange={(e) => setNewItem({ ...newItem, category: e.target.value })}
          required
        >
          <option value="">Select Category</option>
          <option value="PIZZA">Pizza</option>
          <option value="SIDES">Sides</option>
          <option value="BEVERAGES">Beverages</option>
          <option value="COMBOS">Combos</option>
        </select>
        <button type="submit">Create Item</button>
      </form>
      <h2>Menu Items</h2>
      <ul>
        {menuItems.map((item) => (
          <li key={item.id}>
            {item.name} - ${item.price} - {item.category}
            <button onClick={() => handleUpdateItem(item.id, { ...item, price: parseFloat(item.price) + 1 })}>
              Increase Price
            </button>
            <button onClick={() => handleDeleteItem(item.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default AdminDashboard;