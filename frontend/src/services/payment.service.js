import axios from 'axios';
import authHeader from './auth-header';

const API_URL = '/api/payment/';

class PaymentService {

    byContract(contractId) {
        return axios.get(API_URL + `by-contract/${contractId}`, {headers: authHeader()});
    }

    load(paymentID) {
        return axios.get(API_URL + `load/${paymentID}`, {headers: authHeader()});
    }

}

export default new PaymentService();