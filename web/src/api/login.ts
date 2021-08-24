import axios from 'axios';

const baseURI = '';
// 获取backendToken
export const login = (params) => {
	return axios.post(`${baseURI}/login`, params);
};
