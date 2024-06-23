import axios from 'axios';

const baseUrl = 'http://localhost:8080/poems';

const getAll = async () => {
  const response = await axios.get(baseUrl);
  return response.data;
};

const get = async (id) => {
    const response = await axios.get(`${baseUrl}/${id}`);
    return response.data;
  };

const create = async (newPoem) => {
  const response = await axios.post(baseUrl, newPoem);
  return response.data;
};

const update = async (id, updatedPoem) => {
  const response = await axios.put(`${baseUrl}/${id}`, updatedPoem);
  return response.data;
};

const remove = async (id) => {
  await axios.delete(`${baseUrl}/${id}`);
};

const poemService = { getAll, get, create, update, remove };
export default poemService;