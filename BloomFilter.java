package test;
import java.awt.datatransfer.StringSelection;
import java.util.BitSet;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.List;

import static java.lang.Math.abs;

public class BloomFilter
{
    private BitSet _bitSet;
    private int _sizeHash;
    private MessageDigest[] _hashes;

    private int _bigInt;

    public BloomFilter(int size,String...hashes) {
        _sizeHash=size;
        _bitSet=new BitSet(size);
        _hashes = new MessageDigest[hashes.length];
        try {
            for (int i = 0; i <hashes.length;i++)
            _hashes[i]=(MessageDigest.getInstance(hashes[i]));
        }
        catch(Exception e) {
            System.out.println(e);
        }

    }

    public void add(String temp_word) {
        int hash = 0;
        BigInteger hashInt;
        byte[] stringbytes = temp_word.getBytes();
        for (int i = 0; i < _hashes.length; i++) {

            hashInt = new BigInteger(_hashes[i].digest(stringbytes));
            hash = abs(hashInt.intValue()) % _sizeHash;
            _bitSet.set(hash, true);
        }
    }
        public boolean contains (String temp_word){
            int hash = 0;
            BigInteger hashInt;
            byte[] stringbytes = temp_word.getBytes();
            BitSet tmpBitSet = new BitSet(_sizeHash);
            for (int i = 0; i < _hashes.length; i++) {

                hashInt = new BigInteger(_hashes[i].digest(stringbytes));
                hash = abs(hashInt.intValue()) % _sizeHash;
                if (!_bitSet.get(hash))
                {
                    return false;
                }

            }
            return true;
        }

        public String toString () {
            String temp = "";
            for (int i = 0; i < _bitSet.length(); i++) {
                if (_bitSet.get(i))
                    temp += "1";
                else
                    temp += "0";
            }
            return temp;
        }

    }
