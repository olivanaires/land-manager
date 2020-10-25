<template>
    <b-row align-h="center">
        <b-card class="col-md-12">
            <b-card-body>
                <b-row>
                    <b-form-group label="" class="col-md-12">
                        <b-table :items="payments"
                                 :fields="paymentFields"
                                 show-empty empty-text="Sem pagamentos">

                            <template v-slot:cell(description)="data">
                                {{ `${data.item.description} Nº ${data.item.number}` }}
                            </template>

                            <template v-slot:cell(expirationdate)="data">
                                {{ formatDate(data.item.expirationdate) }}
                            </template>

                            <template v-slot:cell(expirationdate)="data">
                                {{ formatDate(data.item.expirationdate) }}
                            </template>

                            <template v-slot:cell(paydate)="data">
                                {{ formatDate(data.item.paydate) }}
                            </template>

                            <template v-slot:cell(value)="data">
                                {{ formatCurrency(data.item.value) }}
                            </template>

                            <template v-slot:cell(paidvalue)="data">
                                {{ formatCurrency(data.item.paidvalue) }}
                            </template>

                            <template v-slot:cell(executed)="data">
                                {{ data.item.executed ? 'Sim' : 'Não'}}
                            </template>

                            <template v-slot:cell(options)="data">
                                <b-link v-on:click="pay(data.item.id)" v-if="!data.item.executed" class="option-item"
                                        v-b-tooltip.hover title="Receber Pagamento">
                                    <b-icon icon="check-square"></b-icon>
                                </b-link>
                            </template>
                        </b-table>
                    </b-form-group>
                </b-row>
            </b-card-body>
        </b-card>
    </b-row>
</template>

<script>
    import moment from 'moment';
    import PaymentService from '../../services/payment.service';

    export default {
        name: 'c-payment-show',
        props: {
            contractId: {required: true},
        },
        data() {
            return {
                payments: [],
                paymentFields: [
                    {
                        key: 'description',
                        label: 'Descrição',
                    },
                    {
                        key: 'expirationdate',
                        label: 'Data Vencimento',
                        formatter: "formatDate"
                    },
                    {
                        key: 'paydate',
                        label: 'Data Pagamento',
                        formatter: "formatDate"
                    },
                    {
                        key: 'value',
                        label: 'Valor',
                        formatter: "formatCurrency"
                    },
                    {
                        key: 'paidvalue',
                        label: 'Valor Pago',
                        formatter: "formatCurrency"
                    },
                    {
                        key: 'executed',
                        label: 'Pago?',
                    },
                    {
                        key: 'options',
                        label: 'Opções',
                        tdClass: 'center',
                        thClass: 'center'
                    }
                ],
            }
        },
        mounted() {
            PaymentService.byContract(this.contractId)
                .then(result => {
                    this.payments = result.data;
                }).catch(error => this.$swal({icon: 'error', title: error.response.data.message}));
        },
        methods: {
            formatDate(value) {
                return moment(String(value), "YYYY-MM-DD").format('DD/MM/YYYY');
            },
            formatCurrency(value) {
                let formatter = new Intl.NumberFormat('pt-BR', {
                    style: 'currency',
                    currency: 'BRL',
                });
                return formatter.format(value);
            },
            pay(paymentId) {
                console.log(paymentId);
                // const indexOf = this.payments.findIndex(item => item.description === payment.description
                //     && item.paymentType === payment.paymentType
                //     && item.date === payment.date);
                //
                // payment = new Payment(payment, this.contractId);
                // ContractService.receivePayment(payment)
                //     .then(response => {
                //         this.payments[indexOf].payed = true;
                //         this.$swal({icon: 'success', title: response.data.message})
                //     })
                //     .catch(error => this.$swal({icon: 'error', title: error.response.data.message}));
            }
        }
    }

</script>