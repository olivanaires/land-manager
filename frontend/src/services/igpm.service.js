import axios from 'axios';
import authHeader from './auth-header';

const API_URL = '/api/igpm/';

class IgpmService {

    apply(igpm) {
        return axios.post(API_URL + 'apply', igpm,{headers: authHeader()});
    }

}

export default new IgpmService();